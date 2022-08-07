# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")

ranges = []
line = file.readline().strip()
while line != "":
	s = line.split(": ")[1].split(" or ")
	r = [ x.split('-') for x in s ]
	r = [ (int(x[0]), int(x[1])) for x in r ]
	ranges.append(r)
	line = file.readline().strip()
file.readline()

myTicket = [ int(x) for x in file.readline().strip().split(',') ]
file.readline()
file.readline()

# Discard invalid tickets
tickets = []
for line in file:
	ticket = [ int(x) for x in line.split(',') ]
	valid = True
	for x in ticket:
		b = False
		for r in ranges:
			if (x >= r[0][0] and x <= r[0][1]) or (x >= r[1][0] and x <= r[1][1]):
				b = True
				break
		if not b:
			valid = False
			break
	if valid: tickets.append(ticket)
file.close()

# Filter invalid ranges
possible = [ [] for _ in range(len(myTicket)) ]
for i in range(len(myTicket)):
	for j in range(len(ranges)):
		r = ranges[j]
		valid = True
		for ticket in tickets:
			x = ticket[i]
			if x < r[0][0] or (x > r[0][1] and x < r[1][0]) or x > r[1][1]:
				valid = False
				break
		if valid: possible[i].append(j)

# Find departure fields
departure = []
while len(departure) != 6:
	for i in range(len(possible)):
		if len(possible[i]) == 1:
			value = possible[i][0]
			for j in range(len(possible)):
				if value in possible[j]:
					possible[j].remove(value)
			if value < 6: departure.append(myTicket[i])
			break

# Multiply
m = 1
for x in departure: m *= x
print(m)
