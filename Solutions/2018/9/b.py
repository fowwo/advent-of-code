# July 10th, 2022

file = open("input.txt", 'r')
input = file.read().strip().split(" ")
file.close()

class Node:
	def __init__(this, value):
		this.value = value
		this.prev = None
		this.next = None
	def insertBefore(this, node):
		node.prev = this.prev
		node.next = this
		if this.prev: this.prev.next = node
		this.prev = node
	def insertAfter(this, node):
		node.prev = this
		node.next = this.next
		if this.next: this.next.prev = node
		this.next = node
	def remove(this):
		this.prev.next = this.next
		this.next.prev = this.prev
		del this

players = int(input[0])
marbles = 100 * int(input[6]) + 1
scores = [ 0 for _ in range(players) ]
node = Node(0)
node.next = node
node.prev = node

for marble in range(1, marbles):
	if marble % 23 == 0:
		for _ in range(7):
			node = node.prev
		new = node.next
		scores[(marble - 1) % players] += marble + node.value
		node.remove()
		node = new
	else:
		new = Node(marble)
		node.next.insertAfter(new)
		node = new

print(max(scores))
