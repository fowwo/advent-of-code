# August 27th, 2022

fish <- scan("input.txt", sep=",")

time <- 0
while (time < 80) {
	i <- 1
	while (i <= length(fish)) {
		fish[[i]] <- fish[[i]] - 1
		if (fish[[i]] < 0) {
			fish[[i]] <- 6
			fish[[length(fish) + 1]] <- 9
		}
		i <- i + 1
	}
	time <- time + 1
}
print(length(fish))
