import heapq
import sys
input = sys.stdin.readline


def find(x):
    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    parentA = find(a)
    parentB = find(b)

    if parentA > parentB:
        parent[parentB] = parentA
    else:
        parent[parentA] = parentB

        
N, M = map(int, input().split())
parent = [i for i in range(N + 1)]
edges = []
for _ in range(M):
    u, v, w = map(int, input().split())
    heapq.heappush(edges, [w, u, v])

total_cost = 0
last_cost = 0

while edges:
    edge = heapq.heappop(edges)

    if find(edge[1]) != find(edge[2]):
        union(edge[1], edge[2])
        total_cost += edge[0]
        last_cost = edge[0]

print(total_cost - last_cost)
