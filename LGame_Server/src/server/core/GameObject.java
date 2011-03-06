package server.core;

/**
 * ��Ϸ�����ֻ࣬Ҫ����Ϸ�У���Id��InstanceId��Name�Ķ��󶼿�����Ϊ��һ����Ϸ���󣬰�����������ͼ����Ʒ��װ���ȵ�
 * ÿ����Ϸ���󶼴���һ����Ϸ�������ã���������Ĵ�ObjectAccessor��ȡ����Ӧ�Ķ���
 * @author Jeffrey
 *
 */
public interface GameObject {
	
	public static final int TYPE_PLAYER = 1; //���
	public static final int TYPE_CREATURE = 2; //�������npc
	public static final int TYPE_ITEM = 3;//��Ʒ����װ��
	public static final int TYPE_MAP = 4;//��ͼ
	public static final int TYPE_PET = 5;//����
	public static final int TYPE_GATHERUNIT = 7;//�ɼ�npc
	public static final int TYPE_TRANSFER = 8; //���͵�
	public static final int TYPE_RELIVEPOINT = 9; //�����

	public long getId();
	
	public int getInstanceId();
	
	public String getName();
	
	
	public GameObjectRef ref();
	/**
	 * GameObject �����ͣ�����������0~32,��չϵͳ���33�Ժ���
	 * @return
	 */
	public int getType();
}
