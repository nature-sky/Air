# Example for Jags-Ybinom-XnomSsubjCcat-MbinomBetaOmegaKappa.R 
#------------------------------------------------------------------------------- 
# Optional generic preliminaries:
graphics.off() # This closes all of R's graphics windows.
rm(list=ls())  # Careful! This clears all of R's memory!
#------------------------------------------------------------------------------- 
# Read the data 
myData = read.csv("output-day.csv")
#------------------------------------------------------------------------------- 
# Load the relevant model into R's working memory:
source("genMCMC.R")
#------------------------------------------------------------------------------- 
# Optional: Specify filename root and graphical format for saving output.
# Otherwise specify as NULL or leave saveName and saveType arguments 
# out of function calls.
fileNameRoot = "air-result-Day-" 
graphFileType = "pdf" 
#-------------------------------------------r------------------------------------ 
# Generate the MCMC chain:
startTime = proc.time()
mcmcCoda = genMCMC( data=myData , 
                    # The column in our data
                    zName="Day00", NName="Dummy", sName="DateStation", cName="Days",
                    numSavedSteps=500 , saveName=fileNameRoot ,
                    thinSteps=20 )
stopTime = proc.time()
elapsedTime = stopTime - startTime
show(elapsedTime)
#------------------------------------------------------------------------------- 
# Display diagnostics of chain, for specified parameters:
parameterNames = varnames(mcmcCoda) # get all parameter names for reference
#for ( parName in c("omega[1]","omegaO","kappa[1]","kappaO","theta[1]") ) { 
#  diagMCMC( codaObject=mcmcCoda , parName=parName , 
#            saveName=fileNameRoot , saveType=graphFileType )
#}
#------------------------------------------------------------------------------- 
# Get summary statistics of chain:
summaryInfo = smryMCMC( mcmcCoda , compVal=NULL , 
                        #diffSVec=c(75,156, 159,844) , 
                        diffCVec=c(1,2,3,4) , # Four times
                        compValDiff=0.0 , saveName=fileNameRoot )
# Display posterior information:
plotMCMC( mcmcCoda , data=myData , 
          # The column in our data
          zName="Day00", NName="Dummy", sName="DateStation", cName="Days", 
          compVal=NULL ,
          diffCList=list( c("Morning","Afternoon") ,
                          c("Afternoon","Evening") ,# Compare Spring and Summer
                          c("Evening","Night") ,
                          c("Night","Morning") ) , # Compare Autumn and Winter
          #diffSList=list( c("2014/01/04","2014/06/13") ), # Compare two dates
          # c("Mike Leake","Wandy Rodriguez") , 
          #c("Andrew McCutchen","Brett Jackson") , 
          #c("ShinSoo Choo","Ichiro Suzuki") ) , 
          compValDiff=0.0, #ropeDiff = c(-0.05,0.05) ,
          saveName=fileNameRoot , saveType=graphFileType )
#------------------------------------------------------------------------------- 
