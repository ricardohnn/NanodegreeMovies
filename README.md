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

At this moment, I would be thinking about going all unit testing and ~~MVP implementing (or trying)~~<sup>1</sup> but I want to keep it fast to reach the last project ASAP, so let's skip that for now.

First question is... what should I use to make my life simple? And it seems a fair answer the following:<br>
https://stackoverflow.com/questions/16902716/comparison-of-android-networking-libraries-okhttp-retrofit-and-volley

Retrofit... here goes nothing...

From the code review that my friends had, I believe I should give a try with Retrofit + RxAndroid + Dagger2, so why not a small preview of it?
* [RxAndroid and Retrofit 2][RXNRTFT2]
* [Retrofit 2][RTFT2] - This will solve my problems with the HTTP requests to retrieve the JSON
* [RxAndroid][RXAND] - This is not needed but... why not? Let's make things more retroactive
* [Dagger 2][DGGR2] - This will solve some dependecies/cleaning code with injection

I got a little lazy and people shared this plugin for POJO creation from json<sup>2</sup>:<br>
https://github.com/robohorse/RoboPOJOGenerator

My classes were created, but I noticed that the detail of each movie will be passed from one activity to another, so let's make that parcelable:<br>
[Parcelable vs Serializable][PCLBXSRLB]<br>
[Parcelable reference][PCLB]

Implementing the architecture based on: [LiveData + ViewModel - part 1][LVDTVMP1]<br>
I liked how the folder/logics structure was developed on this example, so I will try to always adapt the next projects based on this. Also I find quite wrong to add @query for the API key, so I found a better way to add the key in every request:<br>
[OKHTTP reference][OKHTTP]<br>
https://futurestud.io/tutorials/retrofit-2-how-to-add-query-parameters-to-every-request<br>
http://www.vogella.com/tutorials/Retrofit/article.html<br>
https://proandroiddev.com/android-architecture-components-mvvm-part-1-1bd138959535

## Updates

[09/06] <sup>1</sup>
* Guess who changed the idea of MVP implementation?<br>
<tab>At this moment, since I have lost a lot of time trying to figure out how to use Retrofit2 + RxAndroid + Dagger2, I will waste some more time and figure out how to make a MVP implemented on this project (luckily is a small one).
Here are some references:
  * [MVP Architecture][MVPARCH]
* With all the information, I could say that the best sources are:
  * [Vogella Android][VOGAND] - For Android references in general
  * [MVP code ref][MVPCODEREF] - For MVP and MVP + frameworks examples
* Change of hearts was invoked... MVVM! ViewModel... really Google?
  * [Android Application Architecture][ANDAPPARCH]
  * [Adding Android Architecture Components][ADDAAC]
  * [LiveData + ViewModel - part 1][LVDTVMP1]
  * [LiveData + Retrofit + Dagger][LVDTRTFTDG]
  * [MVVM + Android Architecture Components][MVVMAAC]
  * [Android Architecture Components part 1][AACP1]
  * [Android Architecture Components part 2][AACP2]

[09/13] <sup>2</sup>
* More MVVM references and studies, also about the Android Arch. Components usage and data binding in xml.
  * http://www.zoftino.com/android-livedata-examples<br>
  * https://proandroiddev.com/android-architecture-components-network-awareness-using-livedata-1a8d3749734d<br>
  * http://www.vogella.com/tutorials/AndroidDatabinding/article.html<br>
  * http://www.androidauthority.com/data-binding-in-android-709747/<br>
  * https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4<br>
  * https://medium.com/google-developers/android-data-binding-adding-some-variability-1fe001b3abcc<br>
  * https://stackoverflow.com/questions/45205287/android-architecture-component-how-to-insert-repository-parameter<br>
  * [Android Architecture Components part 1][AACP1]<br>
  * [Android Architecture Components part 2][AACP2]
* That POJO plugin created a double field as int, which made the request fail. Keep in mind to always check if all values matches your expectations.

[ADDAAC]:<https://developer.android.com/topic/libraries/architecture/adding-components.html>
[OKHTTP]:<http://square.github.io/okhttp/>
[LOGO]:<https://is1-ssl.mzstatic.com/image/thumb/Purple111/v4/d9/d9/95/d9d995d3-e69b-1cce-3b29-68f2c8870b85/source/256x256bb.jpg>
[UDNANO]:<https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true>
[TMDBAPI]:<https://developers.themoviedb.org/3/getting-started>
[RXNRTFT2]:<https://medium.freecodecamp.org/rxandroid-and-retrofit-2-0-66dc52725fff>
[RTFT2]:<http://square.github.io/retrofit/>
[RXAND]:<https://github.com/ReactiveX/RxAndroid>
[DGGR2]:<http://www.vogella.com/tutorials/Dagger/article.html>
[PCLBXSRLB]:<https://android.jlelse.eu/parcelable-vs-serializable-6a2556d51538>
[PCLB]:<https://developer.android.com/reference/android/os/Parcelable.html>
[MVPARCH]:<http://www.vogella.com/tutorials/AndroidArchitecture/article.html>
[MVPCODEREF]:<https://github.com/googlesamples/android-architecture>
[VOGAND]:<http://www.vogella.com/tutorials/android.html>
[ANDAPPARCH]:<https://developer.android.com/topic/libraries/architecture/guide.html>
[LVDTVMP1]:<https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1>
[LVDTRTFTDG]:<https://proandroiddev.com/mvvm-architecture-using-livedata-rxjava-and-new-dagger-android-injection-639837b1eb6c>
[MVVMAAC]:<https://android.jlelse.eu/android-architecture-components-now-with-100-more-mvvm-11629a630125>
[AACP1]:<https://riggaroo.co.za/android-architecture-components-looking-room-livedata-part-1/>
[AACP2]:<https://riggaroo.co.za/android-architecture-components-looking-viewmodels-part-2/>
[RDMEHELP1]:<https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet>
[RDMEHELP2]:<https://github.com/tchapi/markdown-cheatsheet/blob/master/README.md>
