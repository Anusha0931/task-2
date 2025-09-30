# task-2
WeatherClient.java   # Main application code
#compile the code
javac -cp .;json.jar WeatherClient.java   
#Run the program
java -cp .;json.jar WeatherClient [CityName]
#Example
java -cp .;json.jar WeatherClient London
---
#Example output
Requesting: https://wttr.in/London?format=j1
Weather Report for London
------------------------------
Temperature : 18 °C
Humidity    : 65%
Condition   : Partly cloudy


