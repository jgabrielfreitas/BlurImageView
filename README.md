[![](https://jitpack.io/v/jgabrielfreitas/DataControllerDemo.svg)](https://jitpack.io/#jgabrielfreitas/DataControllerDemo) [![](https://img.shields.io/badge/Language%20-Java-4682b4.svg)](https://jitpack.io/#jgabrielfreitas/DataControllerDemo)
[![](https://img.shields.io/apm/l/vim-mode.svg?maxAge=2592000)]()
[![](https://img.shields.io/badge/API-%2B%2017-lightgrey.svg)]()
[![](https://img.shields.io/badge/Android-4.2-green.svg)]()
![InjectLayout](imgs/BlurImageView-header.png)

## How to
**Step 1.** Add the JitPack repository to your build file
```gradle
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

**Step 2.** Add the dependency
```gradle
dependencies {
		compile 'com.github.jgabrielfreitas:BlurImageView:1.0.1'
	}
```

## How it works? :coffee: + :computer: = :zap:

+ Add `BlurImageView` in your layout:
```xml
<com.jgabrielfreitas.core.BlurImageView
        android:id="@+id/dogBlurImageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        app:radius="24"
        android:layout_margin="@dimen/activity_horizontal_margin"
        android:src="@drawable/little_dog" />
```

**You can add blur at your photo using `radius` in layout XML or using  `.setBlur(INTEGER_VALUE)`** method 

### Sample

Your layout

<img src="imgs/example_1.png" width="300">

Now calling `.setBlur(20)`

<img src="imgs/example_2.png" width="300">


### :warning: IMPORTANT
**MAX_RADIUS = 25**

**MIN_RADIUS = 1**

## License
```
The MIT License (MIT)

Copyright (c) 2016 João Gabriel de Freitas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

