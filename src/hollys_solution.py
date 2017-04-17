from collections import deque
from random import randint
from time import time

# assuming nomenclature where stream is N long, and window is size k
# given input array A[N] and integer k
# functions will return an array of size N-k+1 - since first result is for a complete window

# python 3

def naiveSolution(A, k):

	N = len(A)
	min_vals = []

	for i in range(0, N-k+1):
		min_vals.append(min(A[i:i+k]))

	return min_vals

# Basically, what it does is form a double ended queue of the indices of "left-most" minimum
# vals in the window.  If a new lowest is found while walking rightwards in A, the whole queue can be cleared
# otherwise, you push the new index in from the right, invalidating any indices pointing to vals that are higher.  "rightness"
# trumps and thats important to the linearity.  See discussion about linearity in that bit of the code...

def dequeFromMinSolution(A, k):

	N = len(A)
	min_stack = deque()
	min_vals = []

	# initialize the deque with the first index as "min"
	min_stack.append(0)

	# now walk through the rest and update
	for i in range(1, N):

		if A[i] <= A[min_stack[0]]:
			min_stack.clear()
			min_stack.append(i)
		else:
			# if our lowest value is about to go out of our window, kick it
			if min_stack[0] <= i-k:
				min_stack.popleft()

			# now we need to see how far we unwind the stack to place new value
			# of course the effect on complexity of this part depends how large the stack is 
			# and how far you have to unwind to place new value
			# I'm relatively certain this averages out over the whole vector to be negigible... 
      # at least in either random, mostly ascending, or mostly descending.  
      
      # perhaps there are some weird "harmonic" cases where this goes bad... wavelength/4 ~ k or something
			# maths needed...
      
			placed = 0
			while placed == 0 and len(min_stack) != 0:
				if A[min_stack[-1]] >= A[i]:
					min_stack.pop()
				else: 
					placed = 1

			min_stack.append(i)

		if(i >= k-1): # no result until first window filled...
			min_vals.append(A[min_stack[0]])

	return min_vals


if __name__ == "__main__":

	# initialize test data

	size_of_A = 100000
	k = randint(1, size_of_A-1)
	A = []

	# random array testcase
	for i in range(0,size_of_A):
		A.append(randint(-1000,1000))


	naive_time = time()
	naive_result = naiveSolution(A, k)
	naive_time = time() - naive_time

	deque_time = time()
	deque_result = dequeFromMinSolution(A, k)
	deque_time = time() - deque_time

	n_matching = 0
	for i in range(0, len(naive_result)):
		if naive_result[i] == deque_result[i]:
			n_matching += 1

	print("This is k: ", k, " and N: ", len(A))
	print("And the proportion correct is: ", n_matching/len(naive_result))
	print("Naive solution time: ", naive_time)
	print("Deque solution time: ", deque_time)
