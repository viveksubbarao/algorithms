'''
MinHeap object implementing the min heap data structure
'''
class MinHeap:
	def __init__(self):
		self.min_heap = list()
		self.last = -1

	# return the minimum element
	def peek(self):
		if self.last == -1:
			return None
		return self.min_heap[0]

	# insert into min heap
	def insert(self, item):
		self.min_heap.append(item)
		self.last = self.last + 1
		self.sift_up()

	# remove the min element
	def remove(self):
		if self.last == -1:
			return
		if self.last == 0:
			self.min_heap.pop()
			self.last = self.last - 1
			return
		self.min_heap[0] = self.min_heap.pop()
		self.last = self.last - 1
		self.sift_down()

	# Bubble up the inserted element if it does not follow the min heap order
	def sift_up(self):
		if self.last == -1 or self.last == 0:
			return

		i = self.last
		while i != 0:
			if i % 2:
				n = (i - 1) / 2
			else:
				n = (i / 2) - 1


			if self.min_heap[i] < self.min_heap[n]:
				temp = self.min_heap[i]
				self.min_heap[i] = self.min_heap[n]
				self.min_heap[n] = temp
				i = n
			else:
				break

	# Push down root element if it does not follow the min heap order
	def sift_down(self):
		i = 0
		if self.last == 0 or self.last == -1:
			return

		if self.last % 2:
			stop = (self.last - 2) / 2
		else:
			stop = (self.last - 1) / 2

		while i <= stop:
			n = (2 * i) + 1

			#print i, n
			if self.min_heap[i] > self.min_heap[n]:
				if self.min_heap[n] <= self.min_heap[n+1]:
					temp = self.min_heap[n]
					self.min_heap[n] = self.min_heap[i]
					self.min_heap[i] = temp
					i = n
				else:
					temp = self.min_heap[n+1]
					self.min_heap[n+1] = self.min_heap[i]
					self.min_heap[i] = temp
					i = n + 1
			elif self.min_heap[i] > self.min_heap[n+1]:
				temp = self.min_heap[n+1]
				self.min_heap[n+1] = self.min_heap[i]
				self.min_heap[i] = temp
				i = n + 1
			else:
				break;

	def print_heap(self):
		print self.min_heap

# create a min heap
obj = MinHeap()
obj.insert(10)
obj.insert(20)
obj.insert(3)
obj.insert(5)
obj.insert(13)
obj.insert(-2)
obj.insert(0)
obj.insert(6)
obj.insert(-10)
obj.insert(15)
obj.insert(22)

obj.print_heap()
