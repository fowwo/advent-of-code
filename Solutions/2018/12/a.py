# July 11th, 2022

file = open("input.txt", 'r')
initial = file.readline().strip().split(": ")[1]
rest = file.read().strip().split('\n')
file.close()

notes = {}
for note in rest:
	s = note.split(" => ")
	notes[s[0]] = s[1]

left = 0
right = len(initial) - 1
plants = set(i for i in range(len(initial)) if initial[i] == '#')

for _ in range(20):
	copy = set(plants)
	for i in range(left - 2, right + 3):
		string = ""
		for x in range(i - 2, i + 3):
			string += '#' if x in plants else '.'
		if notes.get(string) == '#':
			copy.add(i)
			left = min(left, i)
			right = max(right, i)
		else:
			copy.discard(i)
	plants = copy
print(sum(plants))
