# March 9th, 2022

# Easier to look at puzzle input instead of parsing
state = "A"
tape = {}
i = 0

for _ in range(0, 12134527):
	if i not in tape:
		tape[i] = 0
	if state == "A":
		if tape[i] == 0:
			tape[i] = 1
			i += 1
			state = "B"
		else:
			tape[i] = 0
			i -= 1
			state = "C"
	elif state == "B":
		if tape[i] == 0:
			tape[i] = 1
			i -= 1
			state = "A"
		else:
			tape[i] = 1
			i += 1
			state = "C"
	elif state == "C":
		if tape[i] == 0:
			tape[i] = 1
			i += 1
			state = "A"
		else:
			tape[i] = 0
			i -= 1
			state = "D"
	elif state == "D":
		if tape[i] == 0:
			tape[i] = 1
			i -= 1
			state = "E"
		else:
			tape[i] = 1
			i -= 1
			state = "C"
	elif state == "E":
		if tape[i] == 0:
			tape[i] = 1
			i += 1
			state = "F"
		else:
			tape[i] = 1
			i += 1
			state = "A"
	elif state == "F":
		if tape[i] == 0:
			tape[i] = 1
			i += 1
			state = "A"
		else:
			tape[i] = 1
			i += 1
			state = "E"
print(sum([tape[x] for x in tape]))
