#! python3

import pyrebase

config = {
  "apiKey": "AIzaSyDqzSSZhlYov3BIxMPCT5l2NrMJYjjPwfo",
  "authDomain": "automeetup.firebaseapp.com",
  "databaseURL": "https://automeetup.firebaseio.com",
  "storageBucket": "automeetup.appspot.com"
  "serviceAccount": "C:\Users\simon\Documents\mprog\final-project\secureKey\AutoMeetup-f15490fb4556.json"
  
}

firebase = pyrebase.initialize_app(config)

# get a database listener
db = firebase.database()

