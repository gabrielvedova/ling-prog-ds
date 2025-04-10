while True:
    name_user = input("Me informe seu nome de usuário: ")
    password_user = input("Me informe sua senha: ")

    if name_user == password_user:
        print("A senha e nome de usuário estão iguais")
    else:
        print("Login realizado com sucesso!")
        break