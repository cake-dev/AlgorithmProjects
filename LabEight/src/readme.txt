Discuss the extent to which your results validate Proposition M in the book (p 473): 

In a linear-probing hash table with M entries and N = α M keys, the average number of probes (under Assumption J)
required for search hits and search misses (or inserts), respectively, is: 

1/2(1 + 1/(1-α)) and 1/2(1/(1-α)^2)

The experiment was to insert N random keys into a hash table with M*N entries.

The results of the experiment are as follows:

+------+-------+-----------------------+
|  M   |   N   |  Avg # probes for a miss|
+------+-------+-----------------------+
|  2   |  1000 |          2.35         |
|      |  10000|          2.47         |
|      | 100000|          2.56         |
|      |1000000|          2.52         |
| 1.5  |  1000 |          4.47         |
|      |  10000|          4.96         |
|      | 100000|          5.05         |
|      |1000000|          4.91         |
| 1.25 |  1000 |          9.56         |
|      |  10000|         12.33         |
|      | 100000|         12.18         |
|      |1000000|         12.89         |
|1.125 |  1000 |         22.22         |
|      |  10000|         41.42         |
|      | 100000|         40.73         |
|      |1000000|         39.74         |
+------+-------+-----------------------+

