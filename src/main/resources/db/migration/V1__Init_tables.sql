create table users(
	id uuid primary key,

	email varchar(255) not null,
	google_id varchar(255),						
    hashed_password varchar(255),
    display_name varchar(100) not null,
    role varchar(50) not null,
    status varchar(50) not null,

    created_at timestamptz not null,
    created_by uuid,
    updated_at timestamptz,
    updated_by uuid,
    deleted_at timestamptz,
    deleted_by uuid,

    constraint chk_completed_update_audit_fields
		check ((updated_at is null) = (updated_by is null)),
    constraint chk_completed_delete_audit_fields
    	check ((deleted_at is null) = ( deleted_by is null))
);

create unique index idx_unique_user_email
	on users(email)
	where (deleted_at is null and deleted_by is null);

create unique index idx_unique_user_google_id
    on users(google_id)
	where (deleted_at is null and deleted_by is null);
------------------------------------------------------------------------------------------------------------------------

create table accounts(
    id bigint primary key,
    user_id uuid not null,						

    name varchar(100) not null,					
    description text,
    status varchar(50) not null,
    balance numeric(14, 2) not null,

    created_at timestamptz not null,
    created_by uuid not null,
    updated_at timestamptz,
    updated_by uuid,
    deleted_at timestamptz,
    deleted_by uuid,

    constraint fk_user_id_ref_user
    	foreign key (user_id)
    	references users(id),
    	
    constraint chk_completed_update_audit_fields
    	check ((updated_at is null) = (updated_by is null)),
    constraint chk_completed_delete_audit_fields
    	check ((deleted_at is null) = ( deleted_by is null))
);

create unique index idx_unique_account_name
    on accounts(name, user_id)
	where (deleted_at is null and deleted_by is null);
------------------------------------------------------------------------------------------------------------------------

create table categories(
    id bigint primary key,
    user_id uuid not null,						

    name varchar(100) not null,					
    description text,

    created_at timestamptz not null,
    created_by uuid not null,
    updated_at timestamptz,
    updated_by uuid,
    deleted_at timestamptz,
    deleted_by uuid,

    constraint fk_user_id_ref_user
		foreign key (user_id)
		references users(id),
		
    constraint chk_completed_update_audit_fields
		check ((updated_at is null) = (updated_by is null)),
    constraint chk_completed_delete_audit_fields
		check ((deleted_at is null) = ( deleted_by is null))
);

create unique index idx_unique_category_name
    on categories(name, user_id)
	where (deleted_at is null and deleted_by is null);
------------------------------------------------------------------------------------------------------------------------

create table tracking_periods(
    id bigint primary key,
    user_id uuid not null,
    source_account bigint,
	auto_fill_destination_account bigint,

    name varchar(100) not null,
    description text,
    status varchar(50) not null,
    type varchar(50) not null,
    start_date timestamptz,
    end_date timestamptz,

    created_at timestamptz not null,
    created_by uuid not null,
    updated_at timestamptz,
    updated_by uuid,
    deleted_at timestamptz,
    deleted_by uuid,

    constraint fk_user_id_ref_user
    	foreign key (user_id)
    	references users(id),
    constraint fk_source_account_ref_account
    	foreign key (source_account)
    	references accounts(id),
    constraint fk_auto_fill_destination_account_ref_account
    	foreign key (auto_fill_destination_account)
    	references accounts(id),
    	
    constraint chk_complete_update_audit_fields
    	check ((updated_at is null) = (updated_by is null)),
    constraint chk_complete_delete_audit_fields
    	check ((deleted_at is null) = ( deleted_by is null))
);

create unique index idx_unique_budget_name_and_type
    on tracking_periods(name, type, user_id)
	where (deleted_at is null and deleted_by is null);
------------------------------------------------------------------------------------------------------------------------

create table allocations(
	id bigint primary key,
	user_id uuid not null,
    period_id bigint not null,
    account_id bigint not null,
    category_id bigint not null,

    frequency integer not null,
    unit_amount numeric(14, 2) not null,
    total_amount numeric(14, 2) not null,

    created_at timestamptz not null,
    created_by uuid not null,
    updated_at timestamptz,
    updated_by uuid,
    deleted_at timestamptz,
    deleted_by uuid,

    constraint fk_user_id_ref_user
    	foreign key (user_id)
    	references users(id),
    constraint fk_period_id_ref_tracking_period
    	foreign key (period_id)
    	references tracking_periods(id),
    constraint fk_account_id_ref_account
    	foreign key (account_id)
    	references accounts(id),
    constraint fk_category_id_ref_category
    	foreign key (category_id)
    	references categories(id),
    
    constraint chk_completed_updated_audit_fields
    	check ((updated_at is null) = (updated_by is null)),
    constraint chk_completed_deleted_audit_fields
    	check ((deleted_at is null) = ( deleted_by is null))
);

create unique index idx_unique_category_allocation_category_id
    on allocations(user_id, period_id desc, account_id, category_id)
	where (deleted_at is null and deleted_by is null);
------------------------------------------------------------------------------------------------------------------------

create table transactions(
	id uuid primary key,
    user_id uuid not null,
    source_account_id bigint,
    destination_account_id bigint,
    category_id bigint,
    allocation_id bigint,
    period_id bigint,
    period_status varchar(50) not null,
    
    name varchar(100) not null,
    description text,
    type varchar(50) not null,
    amount numeric(14, 2) not null,
    transaction_date timestamptz not null,		
    daily_sequence bigint not null,				

    created_at timestamptz not null,
    created_by uuid not null,
    updated_at timestamptz,
    updated_by uuid,
    deleted_at timestamptz,
    deleted_by uuid,

    constraint fk_user_id_ref_user
    	foreign key (user_id)
    	references users(id),
    constraint fk_source_account_id_ref_account
    	foreign key (source_account_id)
    	references accounts(id),
    constraint fk_destination_account_id_ref_account
    	foreign key (destination_account_id)
    	references accounts(id),
    constraint fk_category_id_ref_categories
    	foreign key (category_id)
    	references categories(id),
    constraint fk_allocation_id_ref_allocation
    	foreign key (allocation_id)
    	references allocations(id),
    constraint fk_period_id_ref_tracking_period
    	foreign key (period_id)
    	references tracking_periods(id),	
    
    constraint chk_not_null_account_fields
    	check ((source_account_id is not null) or (destination_account_id is not null)),
    constraint chk_completed_update_audit_fields
		check ((updated_at is null) = (updated_by is null)),
    constraint chk_completed_delete_audit_fields
    	check ((deleted_at is null) = ( deleted_by is null))
);

create index idx_sort_transaction_history
    on transactions(user_id, transaction_date desc, daily_sequence desc)
	where (deleted_at is null and deleted_by is null);
