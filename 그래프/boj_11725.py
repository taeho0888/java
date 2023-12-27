import sys
from collections import defaultdict
input = sys.stdin.readline


def find_parent(n, edges):
    graph = defaultdict(list)
    parents = [0] * (n+1)

    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)

    visited = [False] * (n+1)
    stack = [1]

    while stack:
        node = stack.pop()

        for adj in graph[node]:
            if not visited[adj]:
                parents[adj] = node
                stack.append(adj)
                visited[adj] = True

    return parents[2:]


def main():
    n = int(input())
    edges = [list(map(int, input().split())) for _ in range(n-1)]

    result = find_parent(n, edges)
    for parent in result:
        print(parent)


if __name__ == "__main__":
    main()