# Design document psychic waffle
<sub>Simon Ilic, Universiteit van Amsterdam</sub>

# Activity flow
Below a flowchart of the apps activities is shown. The main activity, wich will be shown on regular startup, is drawn red. A key design idea of the app is to be as simple and fast as possible. In order to beat *datumprikkers* a user should have to go through as little trouble as possible when trying to schedule a meeting. This is also seen by looking at the activity flow for first time users: after creating an account a user is immediatly prompted with a screen to create a new group and schedule a meeting.

![activityflow](/doc/activityflow.png?raw=true "activity flow")

Creating a group will follow whatsapps group creation flow as this will be most intuative to a new user. Groups may be created with non-users who will be sent an invite link to start using the app.

# Database structure
A user database will be maintained in firebase. It will hold all usefull data about a user, e.g. name, emailadress, profilepicture, groups etc. Alongside that database a groups database will be maintained. This group database will hold a list of members of the group, the group name etc. Also in order to find users by emailadress a small table will be maintained mapping emailadresses to user id's.

# Planning a meeting
In order to plan a meeting user will link their google calender to the app. For now a copy of the users calender will be stored on the firebase server and retrieved whenever needed. Ideally I would like to use a server to link all calender live with the app, in order to keep all calender up to date. For now though a fresh copy of the calender will be upoloaded to firebase everytime a user opens the app.