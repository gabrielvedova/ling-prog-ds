
seconds = int(input("Digite o tempo em segundos: "))
days = seconds // 86400
hours = (seconds % 86400) // 3600
minutes = ((seconds % 86400) - hours * 3600) // 60
print(f"{days} dias, {hours} horas e {minutes} minutos")



