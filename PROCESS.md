<sub>Simon Ilic, Universiteit van Amsterdam</sub>

This is a process book. It provides details of insights and steps taken during the development of this project.

> ### Note: the first two days were spent on a rejected project proposal
# day 1 9/1/17
* proposed the project idea
* looked into feasibility of the project
** so far this seems promising as playback seems to be allowed when a video is out of focus but still in view
>
# day 2 10/1/17
* started process book
* found other apps with similar functions:
> * [Flytube](http://www.apkmirror.com/apk/flyperinc/flytube/flytube-1-01-rc4-release/flytube-1-01-rc4-android-apk-download/ "Flytube")
> * [Stream](https://play.google.com/store/apps/details?id=com.djit.apps.stream "Stream: player for youtube")
* this confirms to me that the project is feasible in theory

### Start of project psychic waffle

# day 3 11/1/17
* proposed the idea of an automatic meeting scheduler app
* created design doc

# day 4 12/1/17
* started android studio project
* integrated with firebase
* created login activity
* worked out database structure for groups. I decided on having 2 trees one for groups and one for users, each user will have a list of group ids and each group will have a list of user ids

# day 5 13/1/17
* Presented app idea and design
* I need to confirm if 3rd party calender can be accessed through google's API
* currently looking into a way to check if a user logged in for the first time and working out the logic for what to do if that happens

# day 6 16/1/17
* Dear future me, please remember to fix signing out, so that a user can log in with a diffrent google account
* Working on ConfigureGroupActivity, I find that a lot of functions are used that are also used in CreateGroupActivity but I do not know how to share them with this class
* I still have to figure out the correct database rules
* Decided to delete the databasehelper class as I felt it caused more trouble then it fixed

# day 7 17/1/17
* Today I will focus on implementing firebase side stuff
* Looking into linking google calenders API with my project, ran into some trouble with SHA-1 certificate
* this api request might be perfect: calendar.freebusy.query

# day 8 18/1/17
* WOOOOHOOO finally fixed SHA-1 certificate struggles
* going to look into implementing calender api now

# day 9 19/1/17
* I think I will have o implement some sort of server in order for the calender api to work like I want to, need to check with staff if that is allowed
* Looking into this: https://developers.google.com/identity/protocols/OAuth2ServiceAccount
* Setting up a server that uses 3LO seems a bit too complicated for my skillset, currently looking into push notifications asking users to log in and update their calender info when a meeting is being planned
* Found that firebase has a cloud messaging option (FCM), looking into that now

# day 10 20/1/17
* Building a client for firebase in python that will function as a notification system
* Started asking around for test users
* Working on date picker dialog now

# day 11 21/1/17
* working on meeting planner dialog
* remember to fix rotationstuff (persisting the current state when rotating)