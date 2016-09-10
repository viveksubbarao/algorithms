# Program to find the path with the highest sum from the root for a given tree.

visited = [0] * 16
vertices = [7, 6, 4, 4, 2, 5, 5, 3, 9, 12, 3, 5, 4, 2, 1, 2]
adj = []
all_paths = []
cur_path = []

# Initialize the adjacency matrix of the graph.
for i in range(len(vertices)):
    adj.append([0] *  len(vertices))

def addEdge(u, v):
    adj[u][v] = 1

# Return true if there is not edge from this vertices to any other.
def isLeaf(u):
    for i in range(len(vertices)):
        if adj[u][i] == 1:
            return False
    return True

def dfs(u):
    visited[u] = 1
    cur_path.append(u) # Keep track of the nodes visited
    for i in range(len(vertices)):
        if adj[u][i] == 1 and not visited[i]:
            dfs(i)
    
    # If the node is a leaf, save the path traversed from the root along with the total sum of the path.
    if isLeaf(u):
        s = 0
        for i in cur_path:
            s = s + vertices[i]
        all_paths.append({"sum":s, "path":list(cur_path)})
    
    if cur_path:
        cur_path.pop()

addEdge(0, 1)
addEdge(0, 2)
addEdge(0, 3)
addEdge(1, 4)
addEdge(1, 5)
addEdge(2, 6)
addEdge(2, 7)
addEdge(2, 8)
addEdge(3, 9)
addEdge(3, 10)
addEdge(4, 11)
addEdge(5, 12)
addEdge(7, 13)
addEdge(9, 14)
addEdge(10, 15)

dfs(0)

# Print the path with the highest sum
s = 0
k = 0
for i in all_paths:
    if i["sum"] > s:
        s = i["sum"]
        k = i
for i in k["path"]:
    print vertices[i],