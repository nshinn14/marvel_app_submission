# Marvel Android Code Test

This application is my submission to the Marvel Android App coding test. Entirely written in Kotlin :tada: 
<img src="https://user-images.githubusercontent.com/16260875/72776188-49324b00-3bdf-11ea-8be5-099286bb9b1b.PNG" width="100">, this single screen application utilizes Marvel's developer API (`developer.marvel.com`) and various android libraries to display comic book data to the user.

## Data Models

The models used in this build were fairly simple, as it mainly revolved around a `Comic` model that contained comic book related data that was fetched from the API. The Android Room ORM was used as the apps persistence library, however I did not have time to fully utilize its features, like relational mapping during parsing. I have included other data models in the persistence component such as `Creator`, `Character`, `Story`, `Series`, and `Event`. These models ended up not being used due to the time constraint; however the entity relation aimed to reflect the definitions listed out on the API documentation. This is a diagram of the current relation (that is not fully utilized in the app):

<img src="https://user-images.githubusercontent.com/16260875/72776497-687da800-3be0-11ea-9568-2d37716d6b38.PNG" width="550">

## Networking

For the networking component the breakdown is as follows:
 - Retrofit2 is used as the base HTTP client for interacting with the API
 - OkHTTP is used to intercept the request and add the required paramters with each request
 - Moshi is used as the JSON library for parsing the request into consumable entities
 
 Per the documentation, the request paramters had to include:
 
 ```
apikey - public api key assigned to the developer account
ts - a timestamp (or other long string which can change on a request-by-request basis)
hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
```

The project utilizes a crypto util that ensures that each request is sent with a unique timestamp that is also hashed with the api key and secret. 

Testing the endpoints and requests required exensive use of the Android Profiler to debug the app's request and ensure that the correct paramters and hash was being sent to the server. Another viable option would be using Postman, but the Android Profiler is much more convenient and ready to use (in this case).

## UI

The single screen app consists of a simple RecyclerView and ListView pattern, in order to efficiently display comic content to the user by caching data used by an adapter. A CardView provides a clean frame layout to house text views and image data for each comic object. 

One pitfall of the UI component was loading images from the URL as comic data is being fetched from the API. The Marvel API provides a cover image in the format of a URL, so when I tried to load the image from a bitmap I was constantly running into threading issues. The Picasso library uses a thread pool executor for loading images in background, so it loads the imaged on the UI thread once it finishes downloading.

## Testing

Due to time constraints, the unit test suite tested only the persisted data models and the UI test only tested basic view elements using Espresso for view matching and assertions. Ideally the Instrumented tests would take advantage of Mock network classes and requests and pull data from local .json files to remove the network dependency (which should not be a case in a production app). 

## Screenshots
<img src="https://user-images.githubusercontent.com/16260875/72776266-957d8b00-3bdf-11ea-945b-5bd03ccfbb0c.png" width="350"> <img src="https://user-images.githubusercontent.com/16260875/72776267-96aeb800-3bdf-11ea-82ed-81e49866b8c7.png" width="350">
