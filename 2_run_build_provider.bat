

:: Mandatory parameters to be passed (Version and Tag)

set version=%1
cls
set tag=%2
cls

@echo ' --------------------- START BUILD PROVIDER SIDE ------------------------ '

@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' --------------------- 1. Provider Verification  ------------------------  '
@echo ' -------------------(Publishing Results to Broker) ----------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '

:: Run contract's verification in provider.

call mvn pact:verify -Dpactversion=%version% -Dpacttag=%tag%

cls

@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------- 2. Create Tag  -------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '

:: Create tag for provider version

@echo Status Code
curl --location --request PUT "http://localhost:9292/pacticipants/employee/versions/%version%/tags/%tag%" --header "Content-Type: application/json" --silent --output /dev/null --write-out "%%{http_code}"

cls

@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------- 3. Can I Deploy to Prod?  --------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '
@echo ' ------------------------------------------------------------------------  '

:: Return to root dir.

cd /

:: Try to list folder used to generate results of "can i deploy" (C:\out\)

dir /A:D C:\out\ >nul 2>&1

:: If it exists, remove and recreate it. If not, create it.

if ERRORLEVEL 1 (mkdir C:\out\) else (rmdir c:\out /q /s
mkdir c:\out)

:: Call pact-broker with "can-i-deploy" endpoint to check if is possible to deploy a new version of consumer to production. Write results in "c:/out/can-i-deploy.txt".

@echo Status Code
curl -o "c:/out/can-i-deploy.txt" --location --request GET "http://localhost:9292/can-i-deploy?pacticipant=employee&version=%version%&environment=production" --silent --output /dev/null --write-out "%%{http_code}"

:: Access powershell directory in System32 to run some shell comands.

cd C:\Windows\System32\WindowsPowerShell\v1.0

:: Open file, replace double quotes  to "''" (empty) and generate a new output file (C:\out\result-can-i-deploy.txt).

type C:\out\can-i-deploy.txt | powershell -Command "$input | ForEach-Object { $_ -replace \"\"\"\", \"\" }" > C:\out\result-can-i-deploy.txt

:: Search by "deployable:true" in output file to check if is possible deploy in production environment.

powershell.exe C:\Windows\System32\findstr.exe 'deployable:true' c:\out\result-can-i-deploy.txt

:: If not deployable, throw an error and end the execution. If is deployable, call the pact-broker to confirm the deployment and mark environment as "production".

if errorlevel 1 (

    echo You can't deploy it :/

) else (

        @echo ' ------------------------------------------------------------------------  '
        @echo ' ------------------------------------------------------------------------  '
        @echo ' ------------------------------------------------------------------------  '
        @echo ' --------------------- 4. Record Deploy to Prod  ------------------------  '
        @echo ' ------------------------------------------------------------------------  '
        @echo ' ------------------------------------------------------------------------  '
        @echo ' ------------------------------------------------------------------------  '

        @echo Status Code
        curl --location --request POST "http://localhost:9292/pacticipants/employee/versions/%version%/deployed-versions/environment/27480ae9-ca59-471b-b82c-fd7e1871c7ca" --header "Accept: application/hal+json, application/json, */*; q=0.01" --header "X-Interface: HAL Browser" --header "Content-Type: application/json" --silent --output /dev/null --write-out "%%{http_code}"

)
@echo ' -------------------- FINISH BUILD PROVIDER SIDE ------------------------- '

:: Return to repo dir (specify your root repo dir)

cd C:\<<root repo dir>>