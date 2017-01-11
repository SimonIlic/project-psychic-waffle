# Minor Programmeren UvA final project: psychic waffle
<sub>Simon Ilic, Universiteit van Amsterdam</sub>

A final project for the minor programming at the UvA. This app will allow users to schedule meetings with groups of people. The idea behind the app is to automate the tedious _datumprikker_ process by allowing users to share their digital calender with the app which will then suggest a few candidate times for wich all or most attendees are available. The app is intended to help groups of people on irregular schedules that have to regularly schedule meetings with eachother, for example a commitee of a student association.

The app will feature a list of groups that the user is a part of. From this list the user can select a group to schedule a meeting for. This can be done by selecting a time period and setting the length of the meeting. The app will then check its database for everyone's availability and return possible times for the meeting. The user may then select a meeting time or put it up to a vote. Either way the attendees will be notified through a push notification, asking them to confirm the meeting (or to vote on the proposed dates). After this the meeting will be added to everyone's schedule.

### Minimum viable product
The most basics features of the app will be:
* the ability to create groups and add existing users of the app to those groups
* the ability to auto-schedule a meeting for a group

 > *this is achieved by allowing users to share their digital calender with the app and using an algorithm to find shared free time slots*

### Extra features
Apart from these basic features there is much room for expansion:
* allowing for two diffrent ways to land on a meeting time, either by picking one of the suggested timeslots or by bringing the suggested timeslots up for a vote
* introducing a group admin function

 > *this group admin will be the only one to add or remove people to the group and plan new meetings*
* allowing groups to add special calenders to the scheduling algorithm to take into account

 > *e.g. a holiday calender
* expanding to the ical format allowing for everyone to use the app, regardless of their calender service provider.

To handle the user database and push notifications, firebase will be used. To handle all calender related logic API's for the major calender app providing companies will be used, starting with google's calender API.

The biggest technical challenge will be to write an algorithm that efficiently and effectively finds the best timeslots for a meeting. A major focuspoint to overcome this challenge will be efficiently saving a users calender in a useful datastructure for the algorithm.
