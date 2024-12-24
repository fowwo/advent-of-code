# December 23rd, 2024

file = open(f"{__file__}/../input.txt", "r")

def f(target, nums):
	if len(nums) == 1: return nums[0] == target
	x, y = nums.pop(0), nums[0]
	nums[0] = x + y
	if f(target, list(nums)): return True
	nums[0] = x * y
	if f(target, list(nums)): return True
	return False

n = 0
for line in file:
	a, b = line.strip().split(": ")
	target = int(a)
	nums = list(map(int, b.split(" ")))
	if f(target, nums):
		n += target
file.close()

print(n)
