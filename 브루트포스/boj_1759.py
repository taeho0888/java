import sys
input = sys.stdin.readline

L, C = map(int, input().split())
alphabet = list(input().strip().split())
alphabet.sort()

def dfs(depth, start, ja, mo, str):
    if depth == L:
        if ja >= 2 and mo >= 1:
            print(str)
        return
    for i in range(start, C):
        if alphabet[i] in ["a", "e", "i", "o", "u"]:
            dfs(depth + 1, i + 1, ja, mo + 1, str+alphabet[i])
        else:
            dfs(depth + 1, i + 1, ja + 1, mo, str+alphabet[i])

dfs(0, 0, 0, 0, "")
