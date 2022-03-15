# December 3rd, 2021

file = open(f"{__file__}/../input.txt", "r")
line = file.readline().strip()
length = len(line)


oxygenList = []
co2List = []

while line:
	oxygenList.append(line)
	co2List.append(line)
	line = file.readline().strip()

# Find oxygen value
index = 0
while len(oxygenList) > 1:
	count = 0
	f = '0'
	for number in oxygenList:
		if number[index] == '1':
			count += 1
	if (count >= len(oxygenList) / 2):
		f = '1'
	copy = oxygenList.copy()
	for number in oxygenList:
		if number[index] != f:
			copy.remove(number)
	oxygenList = copy
	index += 1

# Find co2 value
index = 0
while len(co2List) > 1:
	count = 0
	f = '0'
	for number in co2List:
		if number[index] == '1':
			count += 1
	if (count >= len(co2List) / 2):
		f = '1'
	copy = co2List.copy()
	for number in co2List:
		if number[index] == f:
			copy.remove(number)
	co2List = copy
	index += 1

oxygen = 0
co2 = 0
for i in range(length):
	oxygen <<= 1
	co2 <<= 1
	if oxygenList[0][i] == '1':
		oxygen += 1
	if co2List[0][i] == '1':
		co2 += 1

print(oxygen * co2)
