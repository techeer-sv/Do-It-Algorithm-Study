# 책 풀이 참고하였습니다.
# 크루스칼 알고리즘 이용하였습니다.
# 1. 간선 데이터를 비용에 따라 오름차순으로 정렬
# 2. 간선을 하나씩 확인하며 현재의 간선이 사이클을 발생시키는지 확인
# 2.a 사이클이 발생하지 않는 경우 최소 신장 트리에 포함
# 2.b 사이클이 발생하는 경우 최소 신장 트리에 포함시키지 않음
# 3. 모든 간선에 대하여 2번 과정 반복

def solution(n, costs):
    def find_parent(parent, x):
        # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if parent[x] != x:
            parent[x] = find_parent(parent, parent[x])
        return parent[x]

    # 두 원소가 속한 집합을 합치기
    def union_parent(parent, a, b):
        a = find_parent(parent, a)
        b = find_parent(parent, b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b


    # 부모 테이블 초기화
    parent = [0] * (n)
    edges = []

    # 부모 테이블상에서, 부모를 자기 자신으로 초기화
    for i in range(n):
        parent[i] = i

    for c in costs:
        a, b, cost = c
        edges.append((cost, a, b))

    # 간선을 비용순으로 정렬
    edges.sort()

    answer = 0
    # 간선을 하나씩 확인하며
    for edge in edges:
        cost, a, b = edge
        # 사이클이 발생하지 않는 경우에만 집합에 포함
        if find_parent(parent, a) != find_parent(parent, b):
            union_parent(parent, a, b)
            answer += cost

    return answer