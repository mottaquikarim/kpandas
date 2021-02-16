# Pandas Implementation in Kotlin

This is primarily for my own education. 
Learning Kotlin by working on implementing a (mostly) 
1:1 featured pandas implementation in kotlin.

## Progress so far...

```kotlin
val s = Series<Any>(mutableListOf(1,2,null, 3), name ="foo")
var t = s * 5
println(t)
```

```bash

	foo
0	5
1	10
2	nan
3	15

Process finished with exit code 0
```

## Resources / TODOs

* https://github.com/vdmeer/asciitable
* https://github.com/mipt-npm/kmath