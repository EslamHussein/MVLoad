# MVLoad

Download & Cache images,json,xml etc... in memory for Android


## Download TODO puplish it to maven store

    implementation(project(':mvload')) {
        exclude group: 'com.android.support'
    }



## How to Use...
### 1- Loading image

	val imageView:ImageView = ......
	...
	imageView.load(url = ".......",placeHolder = R.drawable.placeholder,errorImage = R.drawable.error_place_holder) 




### 2- Loading Json
#### Load Json Object:
			 
	val client: MVLoadClient= MVLoadClient()
	client.requestAsJsonObject("http://pastebin.com/raw/wgkJgazE")
	{ jsonObject: JSONObject?, throwable: Throwable? ->
		Log.d(TAG, jsonObject?.toString())
    }
    
#### Load Json Array:

	val client: MVLoadClient= MVLoadClient()
	client.requestAsJsonArray("http://pastebin.com/raw/wgkJgazE")
	{ jsonArray: JSONArray?, throwable: Throwable? ->
		Log.d(TAG, jsonObject?.toString())
    }
### 3- Loading Generic Data

1- First , you should implement StreamMapper to serialize/deserialize the data for ByteArray to what you need in this example we will use Gson to convert the bytes to list of objects

  		val mapper= object : StreamMapper<ByteArray, List<PinterestItem>> {
            override fun map(input: ByteArray): List<PinterestItem> {
                return Gson().fromJson(String(input),
                        Array<PinterestItem>::class.java).toList()
            }
        }
        
2- Then... 

	val client: MVLoadClient= MVLoadClient()
	client.requestAsGeneric("http://pastebin.com/raw/wgkJgazE",
                mapper = mapper){ list: List<PinterestItem>?, throwable: Throwable? ->  
                ...
                
                }


# Configuration ...
Add the initialization in your application class 

        val cacheConfig= CacheConfig(cacheSize = 100,inMemoryExpiration = 6000)
        MVLoad.init(cacheConfig)

## Build Requirements

This is an Android Studio project using the Gradle build system, to build this project you need

1. Build tools version 28.0.0-alpha3
2. API level 28
3. Android Studio 3 
4. Gradle Version 3


