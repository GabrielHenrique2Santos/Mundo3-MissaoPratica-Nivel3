
package cadastrobd.model;

/**
 *
 * @author Gabriel
 */
public class pessoaFisica extends pessoa {
    private String cpf;

    
    public pessoaFisica() {
    }

    
    public pessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

   
    @Override
    public void exibir() {
        super.exibir(); 
        System.out.println("CPF: " + cpf);
        }
}