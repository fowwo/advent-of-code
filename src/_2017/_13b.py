# February 21st, 2022

file = open("input/2017/13.txt", "r")

firewall = {}
for line in file:
	split = line.split(": ")
	firewall[int(split[0])] = int(split[1]) - 1

i = 0
caught = True
while caught:
	caught = False
	i += 1
	for x in firewall:
		if abs((x + i - firewall[x]) % (2 * firewall[x]) - firewall[x]) == 0:
			caught = True
			break
print(i)
