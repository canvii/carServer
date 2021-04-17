/**
BasicHTTPClient.ino
Created on: 24.05.2015
*/
#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
ESP8266WiFiMulti WiFiMulti;
void setup()
{
  Serial.begin(115200);
  // Serial.setDebugOutput(true);
  for (uint8_t t = 4; t > 0; t--)
  {
    Serial.printf("[SETUP] WAIT %d...\n", t);
    Serial.flush();
    delay(1000);
  }
  WiFi.mode(WIFI_STA);
  WiFiMulti.addAP("piwifi", "wasd1234");
  Serial.println(WiFi.localIP());
}

static int convertChar(String s)
{
  char m[20];
  strcpy(m, s.c_str());
  int val = atoi(m);
  return val;
}

void loop()
{
  // wait for WiFi connection
  if ((WiFiMulti.run() == WL_CONNECTED))
  {
    WiFiClient client;
    HTTPClient http;
    if (http.begin(client, "http://192.168.12.1:8080/puppy/getxy"))
    {
      // HTTP
      // start connection and send HTTP header
      int httpCode = http.GET();
      // Serial.println(httpCode);
      // httpCode will be negative on error
      if (httpCode > 0)
      {
        // HTTP header has been send and Server response header has been handled
        Serial.printf("[HTTP] GET... code: %d\n", httpCode);
        // file found at server
        if (httpCode == HTTP_CODE_OK || httpCode == HTTP_CODE_MOVED_PERMANENTLY)
        {
          String s = http.getString();
          int index = s.indexOf(":");
          String x = s.substring(0, index);
          String y = s.substring(index + 1, index + 5);
          Serial.println(convertChar(x));
          Serial.println(convertChar(y));
          delay(1000);
        }
      }
      else
      {
        Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
      }
      http.end();
    }
    else
    {
      Serial.printf("[HTTP} Unable to connect\n");
    }
  }
}
