
number = int(input("Digite um número: "))

def tabuada(number):
    for i in range(1, 11):
        print(f"{number} x {i} = {number * i}")

tabuada(number)