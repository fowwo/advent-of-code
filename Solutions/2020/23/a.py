# August 9th, 2022

file = open(f"{__file__}/../input.txt", "r")
input = list(map(int, file.readline().strip()))
file.close()

class Node:
	def __init__(this, value):
		this.value = value
		this.prev = None
		this.next = None

for i in range(len(input)): input[i] = Node(input[i])
for i in range(len(input)):
	input[i].prev = input[(i - 1) % len(input)]
	input[i].next = input[(i + 1) % len(input)]
cup = input[0]
n = len(input)
del input

for i in range(100):
	# Pick up three cups
	a = cup.next
	b = a.next.next

	# Select destination cup
	v = (cup.value - 2) % n + 1
	while v == a.value or v == a.next.value or v == b.value:
		v = (v - 2) % n + 1
	destination = b.next
	while destination.value != v: destination = destination.next

	# Place cups after destination cup
	cup.next = b.next
	b.next.prev = cup
	b.next = destination.next
	destination.next.prev = b
	destination.next = a
	a.prev = destination

	# Select new current cup
	cup = cup.next

while cup.prev.value != 1: cup = cup.next
while cup.value != 1:
	print(cup.value, end='')
	cup = cup.next
print()
