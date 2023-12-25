import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
graph = [list(map(int, input().split())) for _ in range(M)]
parent = [i for i in range(N+1)]

def find(x):
    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]
    
def union(a, b):
    parentA = find(a)
    parentB = find(b)

    if (parentA > parentB):
        parent[parentA] = parentB
    else:
        parent[parentB] = parentA

graph.sort(key= lambda x: x[2])

answer = 0
for a, b, c in graph:
    if find(a) != find(b):
        union(a, b)
        answer += c

print(answer)
    