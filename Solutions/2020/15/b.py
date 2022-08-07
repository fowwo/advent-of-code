# August 6th, 2021

input = [ 1, 0, 16, 5, 17, 4 ]

memo = dict()
for i in range(len(input) - 1):
	memo[input[i]] = i

number = input[-1]
for i in range(len(input) - 1, 30000000 - 1):
	if number not in memo:
		memo[number] = i
		number = 0
	else:
		age = i - memo[number] 
		memo[number] = i
		number = age
print(number)
