# Example for Jags-Ybinom-XnomSsubjCcat-MbinomBetaOmegaKappa.R 
#------------------------------------------------------------------------------- 
# Optional generic preliminaries:
graphics.off() # This closes all of R's graphics windows.
rm(list=ls())  # Careful! This clears all of R's memory!
#------------------------------------------------------------------------------- 
# Read the data 
myData = read.csv("output-year.csv")
#------------------------------------------------------------------------------- 
# Load the relevant model into R's working memory:
source("genMCMC.R")
#------------------------------------------------------------------------------- 
# Optional: Specify filename root and graphical format for saving output.
# Otherwise specify as NULL or leave saveName and saveType arguments 
# out of function calls.
fileNameRoot = "air-result-Year-" 
graphFileType = "pdf" 
#-------------------------------------------r------------------------------------ 
# Generate the MCMC chain:
startTime = proc.time()
mcmcCoda = genMCMC( data=myData , 
                    # The column in our data
                    zName="Day1", NName="Dummy", sName="DateStation", cName="Year",
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
                        diffCVec=c(1,2,3,4,5,6,7,8,9,10,11,12) , # All Year
                        compValDiff=0.0 , saveName=fileNameRoot )
# Display posterior information:
plotMCMC( mcmcCoda , data=myData , 
          # The column in our data
          zName="Day1", NName="Dummy", sName="DateStation", cName="Year", 
          compVal=NULL ,
          diffCList=list( c("Y2014","Y2013") ,
                          c("Y2013","Y2012") ,# Compare Spring and Summer
                          c("Y2012","Y2011") ,
                          c("Y2011","Y2010") ,
                          c("Y2010","Y2009") ,
                          c("Y2009","Y2008") ,
                          c("Y2008","Y2007") ,
                          c("Y2007","Y2006") ,
                          c("Y2006","Y2005") ,
                          c("Y2005","Y2004") ,
                          c("Y2004","Y2003") ) , # Compare Autumn and Winter
          #diffSList=list( c("2014/01/04","2014/06/13") ), # Compare two dates
          # c("Mike Leake","Wandy Rodriguez") , 
          #c("Andrew McCutchen","Brett Jackson") , 
          #c("ShinSoo Choo","Ichiro Suzuki") ) , 
          compValDiff=0.0, #ropeDiff = c(-0.05,0.05) ,
          saveName=fileNameRoot , saveType=graphFileType )
#------------------------------------------------------------------------------- 
