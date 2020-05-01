ECHO "Compile backend..."
call mvn clean install
ECHO "Deploy backend..."
start cmd /k mvn spring-boot:run
cd src\main\java\com\julianfm\wvctf\frontend
ECHO "Compile frontend..."
call npm install
ECHO "Deploy frontend..."
call npm start
ECHO "Done. Happy hacking!"