# August 6th, 2022

input = [ 1, 0, 16, 5, 17, 4 ]

while len(input) != 2020:
	if input[-1] not in input[:-1]:
		input.append(0)
	else:
		input.append([ x for x in reversed(input[:-1]) ].index(input[-1]) + 1)

print(input[-1])
