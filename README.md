# NanodegreeMovies

![logo][LOGO]<br>

This is the first project (Popular Movies) from the Nanodegree Android Developer Program.<br>
Within this repository, I will try to describe each functionality or lib that I might end up using for the implementation.

As always, first things first, the implementation will be based as informed in here:<br>
[Udacity Nanodegree Project 01][UDNANO]

This project will need a TMDB API key in order to make queries with their API, so I will be following this steps:<br>
[TMDB API][TMDBAPI]

Also, since I cannot upload the API KEY, I will use this procedure to remove them from my repository:<br>
https://richardroseblog.wordpress.com/2016/05/29/hiding-secret-api-keys-from-git/

## Starting the implementation

At this moment, I would be thinking about going all unit testing and ~~MVP implementing (or trying)~~ but I want to keep it fast to reach the last project ASAP, so let's skip that for now.

#### Network Requests
First question is... what should I use to make my life simple? And it seems a fair answer the following:<br>
https://stackoverflow.com/questions/16902716/comparison-of-android-networking-libraries-okhttp-retrofit-and-volley

Retrofit... here goes nothing...

From the code review that my friends had, I believe I should give a try with Retrofit + RxAndroid, so why not a small preview of it?
* [RxAndroid and Retrofit 2][RXNRTFT2]
* [Retrofit][RTFT2]
* [RxAndroid][RXAND]

I got a little lazy and people shared this plugin for POJO creation from json:<br>
https://github.com/robohorse/RoboPOJOGenerator

My classes were created, but I noticed that the detail of each movie will be passed from one activity to another, so let's make that parcelable:<br>
[Parcelable vs Serializable][PCLBXSRLB]<br>
[Parcelable reference][PCLB]

---
#### Updates

[09/06]
* Guess who changed the idea of MVP implementation?<br>
<tab>At this moment, since I have lost a lot of time trying to figure out how to use Retrofit2 + RxAndroid + Dagger2, I will waste some more time and figure out how to make a MVP implemented on this project (luckily is a small one). Here are some references:
  * [MVP Architecture][MVPARCH]
* With all the information, I could say that the best sources are:
  * [Vogella Android][VOGAND] - For Android references in general
  * [MVP code ref][MVPCODEREF] - For MVP and MVP + frameworks examples
* Change of hearts was invoked! ViewModel... really Google?
  * [Android Application Arch][ANDAPPARCH]
  * [LiveData + ViewModel - part 1][LVDTVMP1]
  * [LiveData + Retrofit + Dagger][LVDTRTFTDG]


[LOGO]:<https://is1-ssl.mzstatic.com/image/thumb/Purple111/v4/d9/d9/95/d9d995d3-e69b-1cce-3b29-68f2c8870b85/source/256x256bb.jpg>
[UDNANO]:<https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true>
[TMDBAPI]:<https://developers.themoviedb.org/3/getting-started>
[RXNRTFT2]:<https://medium.freecodecamp.org/rxandroid-and-retrofit-2-0-66dc52725fff>
[RTFT2]:<http://square.github.io/retrofit/>
[RXAND]:<https://github.com/ReactiveX/RxAndroid>
[PCLBXSRLB]:<https://android.jlelse.eu/parcelable-vs-serializable-6a2556d51538>
[PCLB]:<https://developer.android.com/reference/android/os/Parcelable.html>
[MVPARCH]:<http://www.vogella.com/tutorials/AndroidArchitecture/article.html>
[MVPCODEREF]:<https://github.com/googlesamples/android-architecture>
[VOGAND]:<http://www.vogella.com/tutorials/android.html>
[ANDAPPARCH]:<https://developer.android.com/topic/libraries/architecture/guide.html>
[LVDTVMP1]:<https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1>
[LVDTRTFTDG]:<https://proandroiddev.com/mvvm-architecture-using-livedata-rxjava-and-new-dagger-android-injection-639837b1eb6c>
[RDMEHELP1]:<https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet>
[RDMEHELP2]:<https://github.com/tchapi/markdown-cheatsheet/blob/master/README.md>
