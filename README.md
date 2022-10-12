
<div align = "center"> 

<p align="center"><img width=12% src="/images/logo.png"></p>

 # The-Movie-DB

A native android app to show all trending movies for this week
</div>

 ## Architecture
 1. Modular Architecture
    - App Uses Modular architecture to separate ui(app module) from data module.
    - App Module handes all UI relate logic i.e activities,fragements and adapters
    - Data Module handles everything to do with data, further divided into packages, i.e remote package that handles remote data and local that handles cachhing of data locally
 2. MVVM Architecture
    - The Apps generally uses MVVM structure, where everything is divided into ui -> viewModel -> Model(Repository and data sources)
    
 ## Frameworks/Libraries 
 1. Hilt -> For Dependency Injection
 2. Room -> For Local Persistence
 3. Timber -> For Logging
 4. Coil -> For Image Loading and Caching
 5. Paging -> To load all data from the api since it is paginated
 6. Navigation Component -> To Create a single page app 
 7. Okhttp Cleint -> Network calls
 8. Gson Converter ->  For data conversion from json to kotlin object and vice versa
 
 ## Assumptions made on the requirements.
 1. Tests Network calls
 2. Local Persistence for Offline use
 3. Image caching 
 
 ## Building Project
 To build the project, follow this steps
 1. Clone this repository.
 2. register for API key from http://themoviedb.org
 3. Get your access token 
 4.  Put your accessToken to data module  
   - path -> data/src/main/cpp/data.cpp
   - In get accessToken function <br/>
    ```
     std::string getAccessToken() {
    std::string accessToken = "<Access Token Here>";
    return accessToken;
}
    ```
