# NanodegreeMovies
This is the first project (Popular Movies) from the Nanodegree Android Developer Program.

Within this repository, I will try to describe each functionality or lib that I might end up using for the implementation.

As always, first things first, the implementation will be based as informed in here:</br>
[Udacity Nanodegree Project 01](https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true)

This project will need a TMDB API key in order to make queries with their API, so I will be following this steps:</br>
[TMDB API](https://developers.themoviedb.org/3/getting-started)

Also, since I cannot upload the API KEY, I will use this procedure to remove them from my repository:<br/>
https://richardroseblog.wordpress.com/2016/05/29/hiding-secret-api-keys-from-git/

### <u>Starting the implementation</u>
At this moment, I would be thinking about going all unit testing and MVP implementing (or trying) but I want to keep it fast to reach the last project ASAP, so let's skip that for now.

#### Network Requests
First question is... what should I use to make my life simple? And it seems a fair answer the following:<br/>
https://stackoverflow.com/questions/16902716/comparison-of-android-networking-libraries-okhttp-retrofit-and-volley

Retrofit... here goes nothing...

From the code review that my friends had, I believe I should give a try with Retrofit + RxAndroid, so why not a small preview of it?<br/>
* [RxAndroid and Retrofit 2](https://medium.freecodecamp.org/rxandroid-and-retrofit-2-0-66dc52725fff)
* [Retrofit](http://square.github.io/retrofit/)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)

I got a little lazy and people shared this plugin for POJO creation from a json text, so why not use it...<br/>
https://github.com/robohorse/RoboPOJOGenerator
