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


