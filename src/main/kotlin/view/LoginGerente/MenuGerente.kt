package view.LoginGerente

class MenuGerente {
    fun start() {
        println("\n========================== RH SimCity ============================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> println("Encerrando o programa...")
                1 -> UserView().startOption()
                2 -> addUser()
                3 -> LibrarianView().startOption()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
}