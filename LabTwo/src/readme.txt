/******************************************************************************
 *  Name:     Jake Bova
 *
 *  Hours to complete assignment (optional): ~= 8 (including sitting there and thinking)
 *
 ******************************************************************************/

Programming Assignment 1: Percolation


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates? This should be something like 3-5 sentences.
 *****************************************************************************/

I implemented percolation.java using a custom percolation class, as well as the WeightedQuickUnionUF class.
The percolation class is essentialy a 2D array of booleans, with a constructor that takes an integer n as a parameter.
I checked whether the system percolates by checking if the top and bottom virtual sites are connected.
I did this by using the WeightedQuickUnionUF class to connect the top and bottom virtual sites to the top and bottom rows of the grid.
Then, I checked if the top and bottom virtual sites were connected using the connected method of the WeightedQuickUnionUF class.



/******************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for values values of n and T when implementing
 *  Percolation.java with QuickFindUF.java.
 *
 *  To do so, fill in the two tables below. Each table must have at least
 *  4 data points, ranging in time from around 0.1 seconds to at most a few
 *  minutes. Do not include data points that takes less than 0.1 seconds.
 *  Each data point should be double the size of the preceding data point
 *****************************************************************************/

(keep T constant; n doubles)
T = 100

 n          time (seconds)      ratio
------------------------------
32          0.110            1.000
64          0.740            6.727
128         13.02            17.59
256         240.1            18.44
(can't do 512 it would take like 2 hours)



(keep n constant; T doubles)
n = 32

 T          time (seconds)     ratio
------------------------------
100        0.105           1.000
200        0.185           1.762
400        0.315           1.703
800        0.500           1.59
1600       1.100           2.20



/******************************************************************************
 *  Using the empirical data from the above two tables, give an estimate
 *  of the running time of PercolationStats.java as function of both n and 
 *  T, using asymptotic notation, like:
 *
 *       O( n^5.0 * T^1.5 )
 *
 *  Recall that with asymptotic notation, you are interested in the
 *  growth rate of run time as input sizes grow - you drop lower-order
 *  terms and constant factors. In class, we've discussed a way to 
 *  estimate polynomial factors from empirical run times.
 *
 *  How does this estimate compare to the theoretical run time growth
 *  rate, according to analysis of your code?
 *
 *****************************************************************************/

Run time estimate:

When doubling n when T is constant, the run time increases by an increasing ratio.
Taking the log log of the run time, and then taking the slope of the line, the slope rounds to 4 (3.697), which is consistent with the theoretical run time growth rate of O(n^4).

When doubling T when n is constant, the run time increases by a factor of ~2.  This is consistent with the theoretical run time growth rate of O(T).
This can be confirmed by taking the log log of the run time, and then taking the slope of the line.  The slope rounds to 1 (0.85), which is consistent with the theoretical run time growth rate of O(T).

So, the empirical run time growth rate is O(n^3.697 * T^0.85) = O(n^4 * T^1).  This is consistent with the theoretical run time growth rate of O(n^4 * T).



/******************************************************************************
 *  Repeat the previous three questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 *****************************************************************************/

(keep T constant; n doubles)
T = 100

 n          time (seconds)   ratio
------------------------------
100         0.150          1.000
200         0.400          2.667
400         1.400          3.50
800         5.700          4.07
1600        41.56          7.29

(keep n constant; T doubles)
n = 100

 T          time (seconds)    ratio
------------------------------
100         0.25          1.000
200         0.40          1.60
400         0.70          1.75
800         1.10          1.57
1600        2.01          1.83

Run time estimate:

When doubling n when T is constant, the run time increases by a factor of ~4.  This is consistent with the theoretical run time growth rate of O(n^2).
This can be confirmed by taking the log log of the run time, and then taking the slope of the line.  The slope rounds to 2 (2.0285), which is consistent with the theoretical run time growth rate of O(n^2).

When doubling T when n is constant, the run time increases by a factor of ~2.  This is consistent with the theoretical run time growth rate of O(T).
This can be confirmed by taking the log log of the run time, and then taking the slope of the line.  The slope rounds to 1 (0.7518), which is consistent with the theoretical run time growth rate of O(T).

So, the empirical run time growth rate is O(n^2.0285 * T^0.7518) = O(n^2 * T^1).  This is consistent with the theoretical run time growth rate of O(n^2 * T).




 
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

None that I know of.


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, TA,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

Kaelan helped me with determining the empirical run time estimates.


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

Have not been able to eliminate backwash.  I figure this would require 2 WeightedQuickUnionUF objects.
One for the top virtual site, and one for the bottom virtual site.  Then, I would have to check if the top
and bottom virtual sites are connected, and if they are, then the system percolates.  I have not attampted to
implement this, however.


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed (or hated) doing it.                                             
 *****************************************************************************/

Good assignment. I thought the sample inputs were cool, as well as the visualizer.