# August 9th, 2022

file = open(f"{__file__}/../input.txt", "r")
input = list(map(int, file.readline().strip()))
file.close()

class Node:
	def __init__(this, value):
		this.value = value
		this.prev = None
		this.next = None

cups = [ None ] + [ Node(x + 1) for x in range(1000000) ]

cups[input[0]].prev = cups[1000000]
cups[input[0]].next = cups[input[1]]
cups[input[-1]].prev = cups[input[-2]]
cups[input[-1]].next = cups[10]
for i in range(1, len(input) - 1):
	cups[input[i]].prev = cups[input[i - 1]]
	cups[input[i]].next = cups[input[i + 1]]

cups[10].prev = cups[input[-1]]
cups[10].next = cups[11]
cups[1000000].prev = cups[999999]
cups[1000000].next = cups[input[0]]
for i in range(11, 1000000):
	cups[i].prev = cups[i - 1]
	cups[i].next = cups[i + 1]
cup = cups[input[0]]
n = 1000000

for i in range(10000000):
	# Pick up three cups
	a = cup.next
	b = a.next.next

	# Select destination cup
	v = (cup.value - 2) % n + 1
	while v == a.value or v == a.next.value or v == b.value:
		v = (v - 2) % n + 1
	destination = cups[v]

	# Place cups after destination cup
	cup.next = b.next
	b.next.prev = cup
	b.next = destination.next
	destination.next.prev = b
	destination.next = a
	a.prev = destination

	# Select new current cup
	cup = cup.next

print(cups[1].next.value * cups[1].next.next.value)
