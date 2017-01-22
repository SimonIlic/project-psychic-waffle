import pyrebase
from pyfcm import FCMNotification

import os

# load in API keys
with open('API_keys/db_key.key', 'r') as key_file:
    db_key = key_file.read().replace('\n', '')
with open('API_keys/fcm_key.key', 'r') as key_file:
    fcm_key = key_file.read().replace('\n', '')

config = {
  "apiKey": db_key,
  "authDomain": "automeetup.firebaseapp.com",
  "databaseURL": "https://automeetup.firebaseio.com",
  "storageBucket": "automeetup.appspot.com",
  "serviceAccount": 'service-accounts\AutoMeetup-f15490fb4556.json'
}

firebase = pyrebase.initialize_app(config)
push_service = FCMNotification(api_key=fcm_key)

def handle_new_meeting_request(fb_db_message):
    """
    Handles incoming date from the 'requests' data stream
    """
    
    print("INCOMING DATA!!!")
    requests = fb_db_message["data"]

    for request_id in requests.keys():
        meeting = requests[request_id]
        group_id = meeting["groupID"]
        group = db.child("groups").child(group_id).get().val()

        add_meeting_request_to_users(group, meeting)
        
        
    
    #group_id = meeting["groupID"]

    #group = db.child("groups").child(group_id).get().val()

def add_meeting_request_to_users(group, meeting):
    """
    Adds a meeting request to all users from a group
    """

    meeting_id = meeting["meetingID"]
    for user_id in group["members"].keys():
        db.child("users").\
        child(user_id).\
        child("requests").\
        child(meeting_id).set(meeting)
    
    
# get a database reference
db = firebase.database()

# set up database listeners
meeting_request_stream = db.child("requests").stream(handle_new_meeting_request)

# test notification
#result = push_service.notify_single_device(registration_id="eyMYMInhoio:APA91bEGoYIbgcXFEZrLB5yBEQb1H_DsyeSmnmJBzVf2Ad5O_3tXjatOm_tjRKlflZE0V0B6Q7x0wMaEob8ohl3dE5HpOi8SW0lFS2lXwWCb5T8H_Kt2cgJXaZEkzjgPBAvrCuiWc2q_", message_title="test notification", message_body="hello this is a test")

print(result)
