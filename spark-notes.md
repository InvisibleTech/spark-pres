#List of Bookmarks
* A lot of Spark's API centers around passing functions to workers
    - The function we pass and the data referenced in it needs to be Serializable (implementing Java’s Serializable interface).
* An RDD in Spark is simply a distributes collection of objects
    - Create RDDs in two ways: loading and external dataset or distributing a collection of objects.
    - An RDD is an immutable, partitioned, logical collection of records.
* RDDs boil down to two types of operations
    - transformations
    - actions
* Transformations Construct RDDs from pervious ones
* Actions compute a result based on an RDD and return it the Driver Program or save it to an external storage system (HDFS, etc.)
* Transformations are lazy, Spark only evaluates them when there's an action.
* Transformations are composed to reduce passes across data.  Spark considers the whole chain of transformations to compute the data needed for the result. Spark will apply each transformation elementwise if the transformation supports it.
* Spark RDD is based on compostion of simple transformations.
* RDDs are reomputed EACH time you hit them with an action, unless you checkpoint it with persist()
* For repeated queries you'll want to persist the data first.
* Two ways creating RDDS:
    - val lines = sc . parallelize ( List ( "pandas" , "i like pandas" ))
    - val lines = sc . textFile ( "/path/to/README.md" )
* Most transformations and some actions depend on passing functions used by Spark to compute the data.

#Transformations
##Element-wise
* Lazy evaluation - only when used in an action
* Many transformations are element-wise, but not all.
* As you derive new RDDs from prior ones using transformations - a graph called a lineage is kept.
* Lineage is used to recompute the RDD if one is lost or part of a persistent RDD is lost.

#Actions
* Produce the output returned to the driver.
* Cause the transformations defining the RDDs to be invoked.
* take vs. collect
    - take is used to take a portion of the data
    - collect can bring back all and therefor too much data.

#Serialization
* Since the driver passes Transformations, Actions amd Data to the workers, the functions and data need to be serialized.
* Scala - need to support Serializable interface). Furthermore, any objects referenced in the code will be serialized too.  Be careful of amount of data.

#Special RDDs
Additional operations are available on RDDs containing certain type of data—for example, statistical functions on RDDs of numbers, and key-value operations such as aggregating data by key on RDDs of key-value pairs.

Example 3-24. Scala squaring the values in an RDD 
val input = sc . parallelize ( List ( 1 , 2 , 3 , 4 )) 
val result = input . map ( x => x * x ) println ( result . collect ())

Example 3-27. Scala flatMap example, splitting lines into multiple words 
val lines = sc . parallelize ( List ( "hello world" , "hi" )) 
val words = lines . flatMap ( line =>
line . split ( " " )) 
words . first () // returns "hello"

#Set Operations on RDDs - Transformations
* Union - is almost mathematical - duplicates are not removed - to improve performance
* Distinct - can remove duplicates but is an expensive process
* Intersection - requires a data shuffle.
* Cartesian Product

#Persistence (Caching)
* Each action against an RDD would normally recompute the lineage - no reuse.  However, by persisting an RDD we eliminate this cost.  Benefits iterations.
* Of course the Resilience would allow the RDD to be recomputed if the persisting node goes down.
* There are various in-memory persistence options and some that spill to disk.
    - Java heap -unserialized
    - Off heap - serialized
    - Disk - serialized
* For memory only modes, old partitions are evicted using an LRU policy.  They are recomputed as needed.
* Bottom line is - Spark will make sure your job doesn't break due to too much memory use.  It may be less performant but it will complete - all things being equal.

#Examples
Example 3-3. Scala parallelize example - with extra stuff 
val lines = sc . parallelize ( List ( "pandas" , "i like pandas", "don't eat pandas" ))

// Let's add some class to this
class MyStuff(var text: String, var len: Int) extends Serializable {
    override def toString = s"MyStuff($text, $len)"
}

val myStuffs = lines.map(line => new MyStuff(line, line.length()))

// Show it all - this is an Action

myStuffs.collect()

// Lets show some Map Reduce
val words = myStuffs.flatMap(o => o.text.split(" "))

val maps = words.map(word => (word, 1))

val reduction = maps.reduceByKey((x, y) => x + y)
reduction.collect()

// Scala filter example 
val macbethRDD = sc.textFile( "../data/books/MacBeth.txt" )
val bloodyScottsRDD = macbethRDD.filter (line => line.contains( "blood" ))

val warAndPeaceRDD = sc.textFile("../data/books/WarAndPeace.txt")
val bloodyRussiansRDD = warAndPeaceRDD.filter (line => line.contains("blood"))

macbethRDD.count()
bloodyScottsRDD.count()

warAndPeaceRDD.count()
bloodyRussiansRDD.count()

val overallGore = bloodyScottsRDD.union(bloodyRussiansRDD)

Example 3-24. Scala squaring the values in an RDD 
val input = sc.parallelize ( List ( 1 , 2 , 3 , 4 )) 
val result = input.map ( x => x * x ) println ( result . collect ())

Example 3-27. Scala flatMap example, splitting lines into multiple words 
val lines = sc.parallelize(List( "hello world" , "hi" )) 
val words = lines.flatMap(line => line.split( " " )) 
words.first() // returns "hello"

#Notes on persistence
While the methods for loading an RDD are largely found in the SparkContext class, the methods for saving an RDD are defined on the RDD classes. In Scala, implicit conversion exists so that an RDD that can be saved as a sequence file is converted to the appropriate type, and in Java explicit conversion must be used.


