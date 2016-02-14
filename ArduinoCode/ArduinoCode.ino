int buttonval = 0;
int buttonstate = LOW;
int lastButtonState = -1;

int potval = 0;
int pot = 0;
int lastPotval = -1;

int soundReading = 0;
int soundDifference = 0;
int soundThreshold = 600;
int soundStatus = 0;
int lastSoundStatus = -1;

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
    buttonval = digitalRead(13);
    if (buttonval == HIGH) {
      buttonstate = 1;
    } else {
      buttonstate = 0;
    }

    if (buttonstate != lastButtonState) {
      Serial.print("b=");
      Serial.print(buttonstate);
      Serial.print("\n");
      lastButtonState = buttonstate;
    }

    pot = analogRead(A0);
    potval = map(pot,0,1023,0,180);

    if (potval != lastPotval) {
      Serial.print("p=");
      Serial.print(potval);
      Serial.print("\n");
      lastPotval = potval;
      delay(50);
    }

    soundDifference = analogRead(A1) - soundReading;
    if(soundDifference>soundThreshold) {
      soundStatus = 1;
    } else {
      soundStatus = 0;
    }
    if (soundStatus != lastSoundStatus) {
      lastSoundStatus = soundStatus;
      Serial.print("s=");
      Serial.print(soundStatus);
      Serial.print("\n");
      delay(50);
    }
}
