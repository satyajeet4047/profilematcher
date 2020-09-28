# profilematcher

A simple app to show profiles in recyclerview with option to accept or decline.
#### Things implemented :
* Fetch data from api if device is online
* Show profile if device is offline
* Action stored in room
* Use of Constraint layouts, RecyclerView, CardView.
* Generic error toast shown in case for API failures.



#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **ui**: View classes along with their corresponding ViewModel.
4. **utils**: Utility classes.

#### Components Used :
* Architecture - MVVM
* LiveData -Architecture Component
* ViewModel - Architecture Component
* RxJava & RxAndroid - For asynchronous tasks
* DataBindingUtil - Used for Data Binding
* Picasso - To Load images from URL
* Room - For database operations
* Retrofit & OKHTTP - Handles network calls

