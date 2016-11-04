'''
Node object representing each node in the binary tree
'''
class Node:
	left = None
	right = None
	val = None

	def __init__(self, value, left, right):
		self.val = value
		self.left = left
		self.right = right

# BFS algortithm to traverse the tree and print the nodes at the given level
def bfs(root, level):
	l = 1
	nodes = [root]
	while l != level:
		for i in range(len(nodes)):
			n = nodes.pop(0)
			if n.left != None:
				nodes.append(n.left)
			if n.right != None:
				nodes.append(n.right)
		if len(nodes):
			l = l + 1
		else:
			break
	return nodes

# formation of the tree
#root = Node(0, Node(1, Node(2, None, None), Node(3, None, None)), Node(4, Node(5, None, None), Node(6, None, None)))
root = Node(0, Node(1, Node(2, None, None), Node(3, None, None)), None)#Node(4, Node(5, None, None), Node(6, None, None)))

nodes = bfs(root, 3)
for n in nodes:
	print n.val,
'''
# print the entire binary tree
l = 1
while 1:
	nodes = bfs(root, l)
	if len(nodes):
		for n in nodes:
			print n.val,
		print ''
	else:
		break;
	l = l + 1
'''
