int buttonval = 0;
int buttonstate = LOW;
int lastButtonState = 0;

int potval = 0;
int pot = 0;
int lastPotval = 0;

int soundReading = 0;
int soundDifference = 0;
int soundThreshold = 500;
int soundStatus = 0;

String gameState = "";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.setTimeout(50);

  pinMode(13, INPUT);
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);

  soundReading = analogRead(A1);
}

void loop() {
  // put your main code here, to run repeatedly:
  String temp = Serial.readString();
  if (temp.length() > 0) {
    gameState = temp;
  }
  
  if (gameState == "one\n") {
    buttonval = digitalRead(13);
    if (buttonval == HIGH) {
      buttonstate = 1;
    } else {
      buttonstate = 0;
    }

    if (buttonstate != lastButtonState) {
      Serial.print(buttonstate);
      lastButtonState = buttonstate;
    }
  }

  if (gameState == "two") {
    pot = analogRead(A0);
    potval = map(pot,0,1023,0,180);

    if (potval != lastPotval) {
      Serial.println(potval);
      lastPotval = potval;
      delay(1);
    }
  }

  if (gameState == "three") {
    soundDifference = analogRead(A1) - soundReading;
    if(soundDifference>soundThreshold) {
      soundStatus = 1;
    } else {
      soundStatus = 0;
    }
    Serial.println(soundStatus);
    delay(100);
  }
}
