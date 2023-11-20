
package cadastrobd.model;

/**
 *
 * @author Gabriel
 */
public class pessoaJuridica extends pessoa{
    private String cnpj;

   
    public pessoaJuridica() {
    }

   
    public pessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

   
    @Override
    public void exibir() {
        super.exibir(); 
        System.out.println("CNPJ: " + cnpj);
    }
}

