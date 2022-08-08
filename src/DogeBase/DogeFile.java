package DogeBase;

import static DogeBase.DogeTypeID.FILE;

public class DogeFile implements DogeType{

    private DogeTypeID m_id = FILE;

    DogeFile(String filename){

    }
    DogeFile(byte[] data){

    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    public void save(String filename){

    }

}
